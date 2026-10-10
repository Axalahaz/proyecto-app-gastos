import type { CategoriaGasto } from "@/modules/gastos/dto/categoria/CategoriaGasto";
import { getCategoriaConfig } from "@/modules/gastos/types/diccionarioCategorias";


type Props = { 
  categoria: CategoriaGasto; 
}

export const CategoriasCard = ({ categoria }: Props) => {
  
  const nombre = categoria.nombre;

  const config = getCategoriaConfig(nombre, categoria.tipo);

  const { 
      iconCategoria, 
      bgCategoria,
      borderCategoria,
  } = config ?? {};

  return (
    
    <div className="flex flex-col justify-center items-center pt-1">
      <div 
        className="w-11 h-11 border rounded-full
        flex justify-center items-center overflow-hidden"
        style={{
          backgroundColor: bgCategoria,
          borderColor: borderCategoria,
        }}
      >
        {iconCategoria &&
          <img
            src={iconCategoria}
            alt={nombre}
            className="w-full h-full p-[6px] object-contain"
          />
        }
      </div>

      <h2 className="text-sm">
        {nombre}
      </h2>
    </div>
  );
};