import { DateCalendar } from "@mui/x-date-pickers";
import { useFilter } from "@/hooks/useFilter";
import { RangeDay } from "./RangeDay";
import type { Dayjs } from "dayjs";
import { useState } from "react";
import type { ThemeProps } from "@/shared/theme/themes";
import { defaultTheme } from "@/shared/theme";

interface Props extends ThemeProps {
    common: {};
    className: string;
    sx: {};
    buttonDays: Dayjs | null;
}

export const RangeCalendario = ({
    common,
    className,
    sx,
    buttonDays,
    theme,
}: Props) => {

    const {
        from,
        to,
        setFrom,
        setTo,
    } = useFilter();

    const [step, setStep] = useState<"START" | "END">("START");
    const [hoveredDay, setHoveredDay] = useState<Dayjs | null>(null);

    const handleChange = (date: Dayjs | null) => {

        if (!date) return;

        if (step === "START") {
            setFrom(date);
            setTo(date);
            setStep("END");
            return;
        }

        if (date.isBefore(from, "day")) {
            setTo(from);
            setFrom(date);
        } else {
            setTo(date);
        }

        setStep("START");
    };

    // ---------------
    // WRAPPER
    const DayComponent = (props: any) => (
        <RangeDay
            {...props}
            from={from}
            to={to}
            hoveredDay={hoveredDay}
            setHoveredDay={setHoveredDay}
            step={step}
            theme={theme}
        />
    );

    console.log("---from: ", from.format("DD/MM"))
    console.log("---to: ", to.format("DD/MM"))

    return (
        <DateCalendar
            {...common}
            className={className}
            value={buttonDays || from}
            onChange={handleChange}
            slots={{
                day: DayComponent
            }}
            disableFuture
            sx={{ 
                ...sx, 
                "& .MuiPickerDay-root.Mui-disabled": {
                color: defaultTheme.colors[900],},
                "& .MuiPickerDay-root": {
                    
                    "&.Mui-selected": {
                        backgroundColor: theme.colors[700],
                        color: theme.colors[100],
                    },
                    
                    "&:hover": {
                        backgroundColor: `${theme.colors[500]}40`,
                        color: theme.colors[700],
                    },
                },
            }}
        />
    );

};