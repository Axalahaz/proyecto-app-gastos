import { Dayjs } from "dayjs";
import dayjs from "dayjs";
import "dayjs/locale/es";

dayjs.locale("es");

const formatDiaMesAno = (fecha: Dayjs) => {
    return fecha.format("DD [de] MMMM, YYYY");
};

const formatMes = (fecha: Dayjs) => {
    const formatted = fecha.format("MMMM [de] YYYY");
    return formatted.charAt(0).toUpperCase() + formatted.slice(1);
};

const formatAnio = (fecha: Dayjs) => {
    return fecha.format("YYYY");
};

const formatDiaMesTabla = (fecha: Dayjs) => {
    return fecha.format("DD/MM");
};

const formatDiaMesAnoDosFechas = (
    fecha1: Dayjs,
    fecha2: Dayjs
) => {
    return `${formatDiaMesAno(fecha1)} - ${formatDiaMesAno(fecha2)}`;
};

export default {
    formatDiaMesAno,
    formatMes,
    formatAnio,
    formatDiaMesAnoDosFechas,
    formatDiaMesTabla,
};