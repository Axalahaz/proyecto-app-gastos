import type { CategoriaGasto } from "@/modules/gastos/dto/categoria/CategoriaGasto";
import { getCategoriaConfig } from "@/modules/gastos/types/diccionarioCategorias";

import { defaultTheme } from "@/shared/theme";


type Props = { 
  categoria: CategoriaGasto; 
}

export const CategoriasCard = ({ categoria }: Props) => {
  console.log("---categoriasCard---") //!!
  const config = getCategoriaConfig(categoria.nombre);
  
  const { icon, label, 
    bg=defaultTheme.colors[100], border=defaultTheme.colors[900] } = config;

  return (
    
    <div className="flex flex-col justify-center items-center pt-1">
      <div 
        className="w-11 h-11 border rounded-full
        flex justify-center items-center overflow-hidden"
        style={{
          backgroundColor: bg,
          borderColor: border,
        }}
      >
        <img
          src={icon}
          alt={label}
          className="w-full h-full p-[6px] object-contain"
        />
      </div>

      <h2 className="text-xs">
        {label}
      </h2>
    </div>
  );
};