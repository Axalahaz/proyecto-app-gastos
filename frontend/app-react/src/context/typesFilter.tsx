import { Dayjs } from "dayjs";

export const FILTERS = {
    DIARIO: { 
        value: "DIARIO",
        label: "Diario",
    },
    MENSUAL: { 
        value: "MENSUAL",
        label: "Mensual",
    },
    ANUAL: { 
        value: "ANUAL",
        label: "Anual",
    },
    PERIODO: { 
        value: "PERIODO",
        label: "Periodo",
    },
} as const;

/*
type FilterType =
    | "DIARIO"
    | "MENSUAL"
    | "ANUAL"
    | "PERIODO";
*/
export type FilterType = typeof FILTERS[keyof typeof FILTERS]["value"];

export type FilterContextType = {
    active: FilterType;
    setActive: (value: FilterType) => void;
    
    selectedDate: Dayjs;
    setSelectedDate: (fecha: Dayjs) => void;

    from?: Dayjs;
    setFrom?: (fecha?: Dayjs) => void;

    to?: Dayjs;
    setTo?: (fecha?: Dayjs) => void;
};

