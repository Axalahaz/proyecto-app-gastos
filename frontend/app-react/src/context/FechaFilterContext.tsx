import { createContext } from "react";
import type { FilterContextType } from "@/context/typesFilter"

export const FilterContext = createContext<FilterContextType | null>(null);