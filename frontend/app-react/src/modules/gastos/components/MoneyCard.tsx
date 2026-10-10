import { Precio } from "@/shared/components/Money/Precio";
import { TypeMoney } from "@/shared/components/Money/TypeMoney";

import type { ThemeProps } from "@/shared/theme/themes";
import { defaultTheme } from "@/shared/theme/defaultTheme";

interface MoneyCardProps extends ThemeProps {
  amount: number;
  typeMoney: string;
  subtitle?: string;
};

export const MoneyCard = ({
  amount,
  typeMoney,
  subtitle,
  theme,
}: MoneyCardProps) => {

  return (
    <div 
      className={`flex flex-col justify-center items-center 
        w-full py-5
        border-l border-b rounded-bl-[40px] 
      `}
      style={{
          backgroundColor: theme.colors[100],
          borderColor: `${theme.colors[500]}50`,
          boxShadow: `0 2px 5px ${theme.colors[300]}80`,
      }}
    >
      <div className="flex items-center gap-5">
        <p 
          className={`text-sm font-semi tracking-wide px-2 rounded-full`}
          style={{
              color: theme.colors[100],
              backgroundColor: theme.colors[700], 
          }}
        >
          {subtitle}
        </p>

        <Precio
          valor={amount}
          theme={theme}
          enteroClassName={`text-4xl font-bold`}
          decimalClassName={`text-2xl`}
        />
      </div>

      <TypeMoney 
        typeMoney={typeMoney}
        className={"text-xs"}
        style={{color: defaultTheme.colors[500]}}
      />
    </div>
  );
};