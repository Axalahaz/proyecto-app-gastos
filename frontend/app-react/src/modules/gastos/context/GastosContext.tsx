import { createContext } from "react";
import type { GastosContextType } from "@/modules/gastos/context/GastosContextType";

export const GastosContext = createContext<GastosContextType | undefined>(undefined);