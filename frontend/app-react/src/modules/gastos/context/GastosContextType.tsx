import type { Gasto } from "@/modules/gastos/dto/gasto/Gasto";
import type { CategoriaGasto } from "@/modules/gastos/dto/categoria/CategoriaGasto";
import type { GastoRecurrente } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrente";
import type { PlantillaGasto } from "@/modules/gastos/dto/plantillaGasto/PlantillaGasto";

export interface GastosContextType {

    // --------------------------
    // GASTOS
    gastos: Gasto[];
    cargarGastos(): Promise<void>;
    gastoSeleccionado: Gasto | null;
    seleccionarGasto(gasto: Gasto | null): void;
    modificarGasto(gasto: Gasto): void;
    registrarGasto(gasto: Gasto): void;
    eliminarGasto(gasto: Gasto): void;

    // --------------------------
    // CATEGORIAS GASTOS
    categoriasGastos: CategoriaGasto[];
    cargarCategoriasGastos(): Promise<void>;
    categoriaGastoSeleccionada: CategoriaGasto | null;
    seleccionarCategoriaGasto(categoria: CategoriaGasto | null): void;
    modificarCategoriaGasto(categoria: CategoriaGasto): void;
    registrarCategoriaGasto(categoria: CategoriaGasto): void;
    eliminarCategoriaGasto(categoria: CategoriaGasto): void;

    // --------------------------
    // GASTOS RECURRENTES
    gastosRecurrentes: GastoRecurrente[];
    cargarGastosRecurrentes(): Promise<void>;
    gastoRecurrenteSeleccionado: GastoRecurrente | null;
    seleccionarGastoRecurrente(gastoRecurrente: GastoRecurrente | null): void;
    modificarGastoRecurrente(gastoRecurrente: GastoRecurrente): void;
    registrarGastoRecurrente(gastoRecurrente: GastoRecurrente): void;
    eliminarGastoRecurrente(gastoRecurrente: GastoRecurrente): void;

    // --------------------------
    // PLANTILLA GASTOS
    plantillasGastos: PlantillaGasto[];
    cargarPlantillasGastos(): Promise<void>;
    plantillaGastoSeleccionada: PlantillaGasto | null;
    seleccionarPlantillaGasto(plantillaGasto: PlantillaGasto | null): void;
    modificarPlantillaGasto(plantillaGasto: PlantillaGasto): void;
    registrarPlantillaGasto(plantillaGasto: PlantillaGasto): void;
    eliminarPlantillaGasto(plantillaGasto: PlantillaGasto): void;

    // --------------------------
    // CALCULADORA
    activeCalculadora: boolean;
    abrirCalculadora(): void;
    prepararNuevoGasto(): void;
    cerrarCalculadora(): void;

}