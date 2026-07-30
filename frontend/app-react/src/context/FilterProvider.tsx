import { useState } from "react";
import {FilterContext} from "@/context/FechaFilterContext"
import { FILTERS } from "@/context/typesFilter"
import type { FilterType } from "@/context/typesFilter"
import type { Dayjs } from "dayjs";
import dayjs from "dayjs";


interface Props {
    children: React.ReactNode;
}

export const FilterProvider = ({ 
    children 
}: Props) => {
    
    const [active, setActive] = useState<FilterType>(FILTERS.MENSUAL.value);
    const [selectedDate, setSelectedDate] = useState(dayjs());
    const [from, setFrom] = useState<Dayjs | undefined>(undefined);
    const [to, setTo] = useState<Dayjs | undefined>(undefined);

    return (
        <FilterContext.Provider 
        value={{ 
            active,
            setActive,
            selectedDate,
            setSelectedDate,
            from,
            setFrom,
            to,
            setTo, 
        }}
        >
            {children}
        </FilterContext.Provider>
    );
}