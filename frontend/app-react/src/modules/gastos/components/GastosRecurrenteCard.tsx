import programadoImage from "@/assets/images/estados/programadoV2.png";
import porVencerImage from "@/assets/images/estados/porVencerV2.png";
import pagadoImage from "@/assets/images/estados/pagadoV2.png";
import vencidoImage from "@/assets/images/estados/vencidoV2.png";

import { statusColors } from "@/shared/theme/statusColors";

import type { GastoRecurrenteViewModel } from "@/modules/gastos/dto/gastoRecurrente/GastoRecurrenteViewModel";
import type { estadoFechaGastoRecurrente } from "@/modules/gastos/types/typeGastos";
import { defaultTheme } from "@/shared/theme";

type Props = {
  gastoRecurrente : GastoRecurrenteViewModel;
}

export const GastosRecurrenteCard = ({gastoRecurrente}: Props) => {

  const statusConfig = {
    programado: {
      timeText: "Faltan:",
      image: programadoImage,
    },
    porVencer: {
      timeText: "¡Atención!",
      image: porVencerImage,
    },
    pagado: {
      timeText: "¡Pagado!",
      image: pagadoImage,
    },
    vencido: {
      timeText: "¡Vencido!",
      image: vencidoImage,
    },
  } as const satisfies Record<estadoFechaGastoRecurrente,  {
    timeText: string;
    image: string;
  }>;

  const { border, shadow, text, badgeBg } = statusColors[gastoRecurrente.estado];
  const { image, timeText } = statusConfig[gastoRecurrente.estado];

  const estaPagado: boolean = "pagado" === gastoRecurrente.estado;

  return (
    <div 
      className={`flex gap-3 text-sm
        rounded-[10px] border px-3 py-3`}
      style={{
        backgroundColor: badgeBg,
        boxShadow: `0 0 5px ${shadow}`,
        borderColor: border,
        color: defaultTheme.colors[900],
      }}
    >
      <img
        src={image}
        alt={gastoRecurrente.gastoRecurrente.descripcion}
        className="w-[7vw] flex-1 flex justify-center "
      />
      <div className="w-[6vw] flex flex-col justify-center">
        <div className="border-b pb-1" style={{borderColor: `${border}50`,}}>
          <h2 className="flex items-center justify-center line-clamp-2">
            {gastoRecurrente.gastoRecurrente.descripcion}
          </h2> 
        </div>
        <p 
          className={`pt-2 leading-none font-beiruti flex flex-col items-center justify-center line-clamp-2`}
          style={{color: text, fontWeight: 600,}}
        >
          <span>
            {timeText === "Faltan:" && gastoRecurrente.diasRestantes === 1
              ? "Falta:"
              : timeText}
          </span>
          {!estaPagado && <span>{gastoRecurrente.diasRestantes} dias</span> }
        </p>
      </div>
    </div>
  );
};