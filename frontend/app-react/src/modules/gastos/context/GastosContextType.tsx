import type { Gasto } from "@/modules/gastos/dto/gasto/Gasto";
import type { CategoriaGasto } from "@/modules/gastos/dto/categoria/CategoriaGasto";
import type { GastoRecurrente } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrente";
import type { PlantillaGasto } from "@/modules/gastos/dto/plantillaGasto/PlantillaGasto";

import type { SidebarMode } from "@/shared/types/typeSidebar";

export interface GastosContextType {

    // --------------------------
    // GASTOS
    gastos: Gasto[];
    cargarGastos(): Promise<void>;
    gastoSeleccionado?: Gasto;
    seleccionarGasto(gasto?: Gasto): void;

    // --------------------------
    // CATEGORIAS GASTOS
    categoriasGastos: CategoriaGasto[];
    cargarCategoriasGastos(): Promise<void>;
    categoriaGastoSeleccionada?: CategoriaGasto;
    seleccionarCategoriaGasto(categoria?: CategoriaGasto): void;

    // --------------------------
    // GASTOS RECURRENTES
    gastosRecurrentes: GastoRecurrente[];
    cargarGastosRecurrentes(): Promise<void>;
    gastoRecurrenteSeleccionado?: GastoRecurrente;
    seleccionarGastoRecurrente(gastoRecurrente?: GastoRecurrente): void;

    // --------------------------
    // PLANTILLA GASTOS
    plantillasGastos: PlantillaGasto[];
    cargarPlantillasGastos(): Promise<void>;
    plantillaGastoSeleccionada?: PlantillaGasto;
    seleccionarPlantillaGasto(plantillaGasto?: PlantillaGasto): void;

    // --------------------------
    // SIDEBAR
    sidebarMode: SidebarMode;
    abrirCalculadora(): void;
    cerrarCalculadora(): void;

}