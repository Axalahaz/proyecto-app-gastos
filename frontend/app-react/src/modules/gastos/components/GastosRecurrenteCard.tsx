import programadoImage from "@/assets/images/estados/programadoV2.png";
import porVencerImage from "@/assets/images/estados/porVencerV2.png";
import pagadoImage from "@/assets/images/estados/pagadoV2.png";
import vencidoImage from "@/assets/images/estados/vencidoV2.png";

import { statusColors } from "@/shared/theme/statusColors";

import type { GastoRecurrenteViewModel } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrenteViewModel";
import type { estadoFechaGastoRecurrente } from "@/modules/gastos/types/typeGastos";

type Props = {
  gastoRecurrente : GastoRecurrenteViewModel;
}

export const GastosRecurrenteCard = ({gastoRecurrente}: Props) => {
  console.log("---gastosRecurrenteCard---") //!!

  const statusConfig = {
    programado: {
      timeText: "Faltan:",
      image: programadoImage,
    },
    porVencer: {
      timeText: "¡Cuidado!",
      image: porVencerImage,
    },
    pagado: {
      timeText: "¡Pagado!",
      image: pagadoImage,
    },
    vencido: {
      timeText: "Vencido:",
      image: vencidoImage,
    },
  } as const satisfies Record<estadoFechaGastoRecurrente,  {
    timeText: string;
    image: string;
  }>;

  const { bg, border, shadow, text, badgeBg} = statusColors[gastoRecurrente.estado];
  const { image, timeText } = statusConfig[gastoRecurrente.estado];

  const estaPagado: boolean = "pagado" === gastoRecurrente.estado;

  return (
    
    <div className={`w-full flex flex-col justify-center pb-1 px-1`}>
      <div className="flex-1 flex">
        <img
          src={image}
          alt={gastoRecurrente.gastoRecurrente.descripcion}
          className="max-w-full max-h-full"
        />
      </div>

      <h2 className="flex-1 flex justify-center items-center text-xs line-clamp-2">
        {gastoRecurrente.gastoRecurrente.descripcion}
      </h2> 
      <p 
        className={`flex-1 flex flex-col items-center justify-center text-xs 
          border-t border-b rounded-[10px] py-1 line-clamp-2`}
        style={{
          backgroundColor: badgeBg,
          borderColor: border,
          boxShadow: `0 0 5px ${shadow}`,
          color: text
        }}
      >
        <span>
          {timeText === "Faltan:" && gastoRecurrente.diasRestantes === 1
            ? "Falta:"
            : timeText}
        </span>
        {!estaPagado && <span>{gastoRecurrente.diasRestantes} dias</span> }
      </p>
    </div>
  );
};