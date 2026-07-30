import { Precio } from "@/shared/components/Precio";

import type { ThemeProps } from "@/shared/theme/themes";
import { defaultTheme } from "@/shared/theme/defaultTheme";

interface MoneyCardProps extends ThemeProps {
  amount: number;
  typeMoney: string;
  subtitle: string;
  subtitleClassName?: string;
};

export const MoneyCard = ({
  amount,
  typeMoney,
  subtitle,
  subtitleClassName = "",
  theme,
}: MoneyCardProps) => {
  console.log("---MoneyCard---") //!!

  return (
    <div 
      className={`pt-1 pb-2 
        flex flex-col justify-center items-center 
        border-b border-l border-r rounded-b-[20px]`}
      style={{
          backgroundColor: theme.colors[100],
          borderColor: `${theme.colors[500]}50`,
          boxShadow: `0 5px 5px ${theme.colors[300]}80`,
      }}
    >
      <p 
        className={`text-sm font-semi tracking-wide ${subtitleClassName}`}
        style={{
            color: theme.colors[100],
            backgroundColor: theme.colors[500], 
        }}
      >
        {subtitle}
      </p>

      <Precio
        valor={amount}
        theme={theme}
        enteroClassName={`text-5xl font-bold`}
        decimalClassName={`text-3xl`}
      />

      <p 
        className={`text-xs`}
        style={{color: defaultTheme.colors[500],}}
      >
        {typeMoney}
      </p>
    </div>
  );
};