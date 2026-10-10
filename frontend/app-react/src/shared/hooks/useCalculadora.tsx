import { useEffect, useRef, useState } from "react";
import { Parser } from "expr-eval";

import { TECLAS_ESPECIALES } from "@/shared/types/typesCalculadora";
import type { Operador } from "@/shared/types/typesCalculadora";
import { OPERADORES } from "@/shared/types/typesCalculadora";
import type { Numeros } from "@/shared/types/typesCalculadora";
import type { Tecla } from "@/shared/types/typesCalculadora";
import { NUMEROS } from "@/shared/types/typesCalculadora";

const parser = new Parser();

//!! hay que sacar el uso del teclado y ponerlo en otro lado
export const useCalculadora = () => {

    const [expresion, setExpresion] = useState<string>("");
    const [total, setTotal] = useState<number>(0);

    const esOperador = (tecla: string): tecla is Operador =>
        OPERADORES.includes(tecla as Operador);

    const esNumero = (tecla: string): tecla is Numeros =>
        NUMEROS.includes(tecla as Numeros);

    // ------------------
    // Metodos de borrado
    const borrar = () => {
        setExpresion(prev => prev.slice(0, -1));
    };

    const limpiar = () => {
        setExpresion("");
        setTotal(0);
    };
    
    // ------------------
    // Calculo resultado final
    const calcular = () => {
        try {
            if (expresion.length === 0) return;

            //----------
            // casos donde no calcula nada
            const ultimo  = (expresion.at(-1)) as Operador;
            if (ultimo && ultimo === OPERADORES[4]) return;
            if (ultimo && ultimo !== OPERADORES[5] && esOperador(ultimo) ) return;
            //----------

            const resultado = parser.evaluate(
                expresion.replaceAll(",", ".") // cambia la coma decimal por punto (mathjs usa decimal con ".")
            ); 
            setTotal(resultado);
        } catch {
            setExpresion("Error");
            setTotal(0);
        }
    };

    

    // ------------------
    // Funciones segun tipo de tecla
    const manejarNumero = (numero: Numeros) => {
        if (total !== 0) {
            setExpresion(numero);
            setTotal(0);
            return;
        }
        setExpresion(prev => prev + numero);
    };

    const manejarOperador = (operador: Operador) => {
        if (expresion.length === 0 && (operador === OPERADORES[4] || operador === OPERADORES[5])) {
            setExpresion(prev => prev + operador);
            return;
        }

        if (expresion.length === 0) return;

        const ultimo = (expresion.at(-1));

        if (ultimo && esOperador(ultimo)) {
            setExpresion(prev => prev.slice(0, -1) + operador);
        } else {
            setExpresion(prev => prev + operador);
        }

        setTotal(0);
    };

    const manejarDecimal = () => {
        const partes = expresion.split(/[+\-*/]/);
        const ultimoNumero = partes.at(-1) ?? "";
        
        if (total !== 0) {
            setExpresion(prev => prev + ",");
            setTotal(0);
            return;
        }
        if (ultimoNumero.includes(",")) return;
        
        setExpresion(prev => prev + ",");
        return;
    };

    // ------------------
    // ACCION TECLA
    const presionarTecla = (tecla: Tecla) => {

    if (tecla === TECLAS_ESPECIALES.IGUAL) {
        return calcular();
    }

    if (esNumero(tecla)) {
        return manejarNumero(tecla);
    }

    if (esOperador(tecla)) {
        return manejarOperador(tecla);
    }

    if (tecla === TECLAS_ESPECIALES.COMA) {
        return manejarDecimal();
    }
};

// ----------------
// PERMITE USO DE TECLADO
// Sensibilidad borrado
const timeoutRef = useRef<number | null>(null);

const esElementoEditable = (target: EventTarget | null) => {
    const elemento = target as HTMLElement | null;

    if (!elemento) return false;

    if (
        elemento.tagName === "INPUT" ||
        elemento.tagName === "TEXTAREA"
    ) {
        const input = elemento as HTMLInputElement | HTMLTextAreaElement;

        return !input.readOnly && !input.disabled;
    }
}
    useEffect(() => {
        const keyDown = (e: KeyboardEvent) => {
            if (e.key !== "Backspace") return;
            if (esElementoEditable(e.target)) return;
            e.preventDefault();
            if (e.repeat) return;
            timeoutRef.current = window.setTimeout(() => {
                limpiar();
                timeoutRef.current = null;
            }, 600);
        };

        const keyUp = (e: KeyboardEvent) => {
            if (e.key !== "Backspace") return;
            if (esElementoEditable(e.target)) return;
            if (timeoutRef.current) {
                clearTimeout(timeoutRef.current);
                timeoutRef.current = null;
                borrar();
            }
        };

        window.addEventListener("keydown", keyDown);
        window.addEventListener("keyup", keyUp);
        return () => {
            window.removeEventListener("keydown", keyDown);
            window.removeEventListener("keyup", keyUp);
        };
    }, []);

    useEffect(() => {
        const manejarTecla = (e: KeyboardEvent) => {

    if (esElementoEditable(e.target)) return;

    const tecla = e.key;

    if (
        [...NUMEROS, ...OPERADORES, ","].includes(tecla as any)
    ) {
        presionarTecla(tecla as Tecla);
    }

    if (tecla === ".") presionarTecla(",");

    if (tecla === "Enter") calcular();

    if (tecla === "Backspace") borrar();

    if (tecla === "Escape") limpiar();
};

        window.addEventListener("keydown", manejarTecla);

        return () => window.removeEventListener("keydown", manejarTecla);
    }, [presionarTecla]);
    
    return {
        total,
        expresion,
        borrar,
        limpiar,
        calcular,
        presionarTecla,
    };
};