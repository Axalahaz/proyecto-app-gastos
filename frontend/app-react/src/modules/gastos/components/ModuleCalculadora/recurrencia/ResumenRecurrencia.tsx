import type { FormularioGasto } from '@/modules/gastos/dto/gasto/FormularioGasto';
import { MES} from '@/modules/gastos/types/typeGastos';
import { defaultTheme } from '@/shared/theme';
import type { ThemeProps } from '@/shared/theme/themes';
import EditNoteIcon from '@mui/icons-material/EditNote';

interface Props extends ThemeProps{ 
    gasto: FormularioGasto;
    onClick: () => void;
}

export const ResumenRecurrencia = ({
    gasto,
    onClick,
    theme,
}: Props) => {
    const nombreMes = (MES.find(m => m.value === gasto.mesVencimiento))?.label;
    const frecuencia = gasto.frecuencia;
    const dia = gasto.diaVencimiento;
    const mes = gasto.mesVencimiento;

    return (
        <div 
            className="flex items-center justify-between font-beiruti
            ps-2 py-2 gap-3 rounded-[5px]"
            style={{
                boxShadow: `0 1px 4px ${theme.colors[300]}`,
                background: theme.colors[100],
            }}
        > 
            <div className='flex flex-col '>
                <p 
                    className='text-xl  leading-none' 
                    style={{color: theme.colors[900]}}
                >
                    {frecuencia} - {mes ? `${dia} de ${nombreMes}` : `Dia: ${dia}`}
                </p>
                <p 
                    className='text-base leading-none' 
                    style={{color: defaultTheme.colors[700]}}
                >
                    Toca el lápiz para editar la frecuencia
                </p>
            </div>
            <button 
                className="rounded-full w-10 h-10 active:scale-80  
                hover:bg-[var(--button-hover)] 
                transition-all"
                style={{
                    borderColor: theme.colors[700],
                    "--button-hover": `${theme.colors[300]}50`,
                } as React.CSSProperties}
                onClick={onClick}
            >
                <EditNoteIcon sx={{fontSize: 35, color: theme.colors[700]}}/>
            </button>
        </div>
    );
}