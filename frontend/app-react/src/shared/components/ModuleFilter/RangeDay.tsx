import { PickerDay } from "@mui/x-date-pickers/PickerDay";
import type { PickerDayProps } from "@mui/x-date-pickers/PickerDay";
import type { Dayjs } from "dayjs";
import type { ThemeProps } from "@/shared/theme/themes";

interface Props extends PickerDayProps, ThemeProps {
    from: Dayjs;
    to: Dayjs;
    hoveredDay: Dayjs | null;
    setHoveredDay: (day: Dayjs | null) => void;
    step: string;
}

export const RangeDay = ({
    day,
    from,
    to,
    hoveredDay,
    setHoveredDay,
    step,
    theme,
    ...other
}: Props) => {

    const isStart = day.isSame(from, "day");
    const isEnd = day.isSame(to, "day");

    const isBetween =
        day.isAfter(from, "day") &&
        day.isBefore(to, "day");

    const isInRange = isBetween || isStart || isEnd;

    const isHoveringRange = hoveredDay  && hoveredDay.isAfter(from, "day") 
        && day.isAfter(from, "day") 
        && day.isBefore(hoveredDay, "day") && step === "END";

    const isHoverEnd = hoveredDay && day.isSame(hoveredDay, "day") && step === "END";
    const isHoverStart = hoveredDay && day.isSame(hoveredDay, "day") && step === "START";

    return (
        <PickerDay
            {...other}
            day={day}
            selected={false}
            onMouseEnter={() => setHoveredDay(day)}
            onMouseLeave={() => setHoveredDay(null)}
            sx={{
                color: theme.colors[700],

                ...(isInRange && {
                    backgroundColor: theme.colors[300],
                    borderRadius: 0,
                    color: theme.colors[100],
                }),

                ...(isStart && {
                    backgroundColor: theme.colors[500],
                    borderRadius: "50% 0 0 50%",
                }),

                ...(isEnd && {
                    backgroundColor: theme.colors[500],
                    borderRadius: "0 50% 50% 0",
                }),

                ...(isStart && isEnd && {
                    borderRadius: "50%",
                }),

                ...(isHoveringRange && {
                    backgroundColor: `${theme.colors[300]}80`,
                }),

                ...(isHoverStart && {
                    backgroundColor: `${theme.colors[300]}80`,
                    borderRadius: "50% 0 0 50%",
                }),

                ...(isHoverEnd && {
                    backgroundColor: `${theme.colors[300]}80`,
                    borderRadius: "0 50% 50% 0",
                }),
                
            }}
        />
    );
};