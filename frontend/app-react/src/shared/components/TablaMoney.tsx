import { Precio } from "@/shared/components/Money/Precio";

import type { Gasto } from "@/modules/gastos/dto/gasto/Gasto";

import EditNoteIcon from '@mui/icons-material/EditNote';
import DeleteOutlinedIcon from '@mui/icons-material/DeleteOutlined';

import formatFecha from "@/shared/utils/stringFechaFormat";
import type { ThemeProps } from "@/shared/theme/themes";

import { defaultTheme } from "@/shared/theme";
import { ESTADO_MOVIMIENTO, type estadoMovimiento } from "../types/typesApp";

interface TablaMoneyProps extends ThemeProps {
  filtroEstado: estadoMovimiento | "TODOS"; 
  items: Gasto[];
  onItemFila: (item: Gasto | null) => void;
  filaClassName?: string;       
  className?: string;
  onEdit: () => void;
};

export const TablaMoney = ({
  filtroEstado,
  items,
  onItemFila,
  className = "",
  filaClassName = "",
  theme,
  onEdit,
}: TablaMoneyProps) => {

  const fontFecha: string = "font-beiruti";

  // ------------------
  // Items filtrados por estado
  let itemsFiltradoEstado: Gasto[] = items;

  if(filtroEstado !==  "TODOS") {
    itemsFiltradoEstado = items.filter(i => i.estado === filtroEstado);
  }

  const itemsOrdenadosPorFecha: Gasto[] = [...itemsFiltradoEstado].sort(
    (a, b) => b.fechaCreacion.valueOf() - a.fechaCreacion.valueOf()
);

  const mensaje: string = filtroEstado === "TODOS"
    ? "No hay gastos en esta categoría"
    : filtroEstado === ESTADO_MOVIMIENTO.ANULADO 
        ? "No hay gastos anulados"
        : "No hay gastos activos"

  // ------------------
  // Comportamiento al no recibir datos
  if (itemsFiltradoEstado.length === 0) {
        return (
            <div className="flex flex-col items-center justify-center mt-5 gap-2">
                <p 
                  className="text-sm border-b border-t rounded-[10px] px-5 py-1"
                  style={{
                    color: `${defaultTheme.colors[500]}`,
                    borderColor: `${defaultTheme.colors[100]}`
                  }}
                >
                    {mensaje}
                </p>
            </div>
        );
    }

  return (
    <div 
      className={`flex-1 flex flex-col gap-1
        border-t rounded-[10px] py-1 ${className}`}
      style={{ borderColor: `${defaultTheme.colors[100]}`,}}
    >
      {itemsOrdenadosPorFecha.map((item) => (
          <div 
            key={`itemGasto-${item.id}`}
            className={`flex items-center justify-between ${filaClassName}`}
            style={{
              color: defaultTheme.colors[500], 
              borderColor: defaultTheme.colors[100],
            }}
          >
            <p className={`flex-1 tracking-wide text-sm ${fontFecha}`}>
              {formatFecha.formatDiaMesTabla(item.fechaCreacion)}
            </p>
            <h1
              className={`flex-[2] text-sm`}
              style={{ color: defaultTheme.colors[900],}}
            >
              {item.descripcion ? item.descripcion : "S/N"}
            </h1>
            {filtroEstado ===  "TODOS" 
              && <h1 
                className={`flex-[2] tracking-wider text-sm`}
                style={{ color: defaultTheme.colors[900],}}
              >
                {item.estado.toLocaleLowerCase()}
              </h1>
            }
            <div className={`flex-[2] text-end pe-5`}>
              <Precio
                valor={item.monto}
                theme={theme}
                enteroClassName={`text-lg font-semibold`}
                decimalClassName={`text-xs font-semibold`}
              />
            </div>
            <div className="flex items-center justify-center border-l px-2 gap-2" style={{ borderColor: `${defaultTheme.colors[100]}`,}}>
              <button 
                className="" 
                onClick={()=> {
                  onEdit(); 
                  onItemFila(item);
                }}
              >
                <EditNoteIcon sx={{fontSize: 22,}} />
              </button>
              <button 
                className=""
                onClick={()=> {
                  onItemFila(item);
                }}
              >
                <DeleteOutlinedIcon sx={{fontSize: 20,}} />
              </button>
            </div>
          </div>
      ))}
    </div>
  );
  
};