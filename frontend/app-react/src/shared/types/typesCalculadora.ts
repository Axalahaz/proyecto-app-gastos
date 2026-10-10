export const OPERADORES = ["+", "-", "*", "/", "(", ")"] as const ;
export type Operador = (typeof OPERADORES)[number];

export const NUMEROS = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "0"] as const ;
export type Numeros = (typeof NUMEROS)[number];

export const TECLAS_ESPECIALES = {
    IGUAL: "=",
    COMA: ",",
    BORRAR: "CE",
    LIMPIAR: "C",
} as const;
export type TeclaEspecial = typeof TECLAS_ESPECIALES[keyof typeof TECLAS_ESPECIALES];

export type Tecla = Operador | Numeros | TeclaEspecial;