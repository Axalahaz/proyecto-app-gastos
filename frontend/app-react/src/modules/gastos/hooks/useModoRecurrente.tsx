import { useEffect, useState } from "react";
import type { GastoRecurrente } from "../dto/gastoRecurrente/GastoRecurrente";
import type { FormularioGasto } from "../dto/gasto/FormularioGasto";
import { FRECUENCIA } from "../types/typeGastos";

type Props = {
    formGasto: FormularioGasto;
    setFormGasto: React.Dispatch<React.SetStateAction<FormularioGasto>>;
    gastosRecurrentes: GastoRecurrente[];
    gastoRecurrenteSeleccionado: GastoRecurrente | null;
}

export const useModoRecurrente = ({
    formGasto,
    setFormGasto,
    gastosRecurrentes,
    gastoRecurrenteSeleccionado,
}: Props) => {
    
    const [esRecurrente, setEsRecurrente] = useState(false);

    useEffect(() => {
        const recurrenteId = formGasto.gastoRecurrenteId ?? null;
        if(recurrenteId) {
            const gastoRecurrente = gastosRecurrentes.find(r => r.id === recurrenteId);
            setEsRecurrente(true);
            if(gastoRecurrente) {
                setFormGasto(prev => ({
                    ...prev,
                    diaVencimiento: gastoRecurrente.diaVencimiento,
                    mesVencimiento: gastoRecurrente.mesVencimiento ?? undefined,
                    frecuencia: gastoRecurrente.frecuencia,
                }));
            }
            return;
        }
        
        if (!gastoRecurrenteSeleccionado && esRecurrente) {
            setFormGasto(prev => ({
                ...prev,
                diaVencimiento: 1,
                frecuencia: FRECUENCIA.MENSUAL,
            }));
            return;
        } 
    }, [esRecurrente])
    
    useEffect(() => {
        if(gastoRecurrenteSeleccionado) {
            setEsRecurrente(true);
            setFormGasto(prev => ({
                ...prev,
                diaVencimiento: gastoRecurrenteSeleccionado.diaVencimiento,
                mesVencimiento: gastoRecurrenteSeleccionado.mesVencimiento ?? undefined,
                frecuencia: gastoRecurrenteSeleccionado.frecuencia,
            }));
            return;
        }

    }, [gastoRecurrenteSeleccionado]);
    
    return {
        esRecurrente,
        setEsRecurrente,
    };
}