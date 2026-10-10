import { Precio } from "@/shared/components/Money/Precio";
import type { ThemeProps } from "@/shared/theme/themes";
import { defaultTheme } from "@/shared/theme";

import { TypeMoney } from "@/shared/components/Money/TypeMoney";

// !! Despues poner el tipo de moneda desde configuracion contexto

interface Props extends ThemeProps {
  total: number;
  expresion?: string;
  typeMoney?: string;
  buttonClean?: boolean;
};

export const MoneyCalculatorContenedor = ({
  total,
  expresion,
  theme,
}: Props) => {

  return (
    <div className={`flex flex-col text-center`}>
      {/** Visor Calculadora */}
      {expresion 
        && (
          <p 
            className="font-beiruti p-2 text-sm leading-none overflow-hidden break-work whitespace-normal"
            style={{
              color: defaultTheme.colors[700],
              borderColor: defaultTheme.colors[300],
            }}
          >
            (Cuenta: {expresion})
          </p>
      )}
      {/** Monto TOTAL */}
      <div 
        className="gap-5 rounded-[5px] pt-2 mb-2"
        style={{
            boxShadow: `0 1px 4px ${theme.colors[300]}`,
            background: theme.colors[100],
        }}
      >
        <Precio
            valor={total}
            theme={theme}
            enteroClassName={`text-3xl leading-none font-semibold`}
            decimalClassName = {"text-xl"}
        />
      </div>
      <div 
        className="font-beiruti flex justify-center gap-2 text-sm"
        style={{color: defaultTheme.colors[500]}}
      >
        <p>Moneda: </p>
        <TypeMoney 
          typeMoney={"Peso Arg"}
        />
      </div>
      


      </div>

  );
};