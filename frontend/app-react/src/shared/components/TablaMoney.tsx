import { Precio } from "@/shared/components/Precio";

import type { Gasto } from "@/modules/gastos/dto/gasto/Gasto";

import NavigateNextIcon from '@mui/icons-material/NavigateNext';

import formatFecha from "@/shared/utils/stringFechaFormat";
import type { ThemeProps } from "@/shared/theme/themes";

import { defaultTheme } from "@/shared/theme";

interface TablaMoneyProps extends ThemeProps {
  items?: Gasto[];
  emptyMessage?: string;       // contenedor
  filaClassName?: string;       
  className?: string;
};

export const TablaMoney = ({
  items = [],
  emptyMessage = "",
  className = "",
  filaClassName = "",
  theme,
}: TablaMoneyProps) => {
  console.log("---tablaMoney---") //!!

  const fontFecha: string = "font-beiruti";

  // ------------------
  // Comportamiento al no recibir datos
  if (items.length === 0) {
        return (
            <div className="flex flex-col items-center justify-center mt-5 gap-2">
                <p 
                  className="text-sm border-b border-t rounded-[10px] px-5 py-1"
                  style={{
                    color: `${defaultTheme.colors[500]}`,
                    borderColor: `${defaultTheme.colors[100]}`
                  }}
                >
                    {emptyMessage}
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
      {items.map((item) => (
          <div 
            key={`itemGasto-${item.id}`}
            className={`flex items-center justify-between ${filaClassName}`}
            style={{
              color: defaultTheme.colors[500], 
              borderColor: defaultTheme.colors[100],
            }}
          >
            <p className={`flex-1 text-xs ${fontFecha}`}>
              {formatFecha.formatDiaMesTabla(item.fechaCreacion)}
            </p>
            <h1
              className={`flex-[2] text-sm `}
              style={{ color: defaultTheme.colors[900],}}
            >
              {item.descripcion}
            </h1>
            <div className={`flex-[2] text-end pe-1`}>
              <Precio
                valor={item.monto}
                theme={theme}
                enteroClassName={`text-lg font-semibold`}
                decimalClassName={`text-xs font-semibold`}
              />
            </div>
            <button className="ps-1">
              <NavigateNextIcon sx={{fontSize: 20,}} />
            </button>
          </div>
      ))}
    </div>
  );
  
};