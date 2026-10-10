import { ButtonCalculadora } from "@/shared/components/calculadora/ButtonCalculadora";
import type { ThemeProps } from "@/shared/theme/themes";
import type { Tecla } from "@/shared/types/typesCalculadora";

interface Props extends ThemeProps {
    onClick: (value: Tecla) => void;
    onDelete?: () => void;
    onClear?: () => void;
}

export const Teclado = ({
    onClick,
    onDelete,
    onClear,
    theme,
}: Props) => {
    const teclas: Tecla[] = [
        "CE", "C", "(", ")",
        "7", "8", "9", "-",
        "4", "5", "6", "+",
        "1", "2", "3", "/",
        "*", "0", ",", "="
    ];

    return (
        <section 
            className="flex-1 rounded-[28px] p-3 border" 
            style={{
                background: theme.colors[100],
                borderColor: `${theme.colors[500]}50`,
                boxShadow: `0 -2px 2px ${theme.colors[300]}20`,
                color: theme.colors[700]
            }}
        >
            <div className="grid grid-cols-4 gap-1">
                {teclas.map((tecla) => (
                    <ButtonCalculadora
                        key={tecla}
                        value={tecla}
                        onClick={onClick}
                        onDelete={onDelete}
                        onClear={onClear}
                        theme={theme}
                    />
                ))}
            </div>
        </section>
    );
}