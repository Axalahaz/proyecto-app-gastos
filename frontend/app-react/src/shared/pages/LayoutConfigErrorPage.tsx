import { Link } from "react-router-dom";
import ErrorOutlineOutlinedIcon from '@mui/icons-material/ErrorOutlineOutlined';

export const LayoutConfigErrorPage = () => {
    return (
        <div className="min-h-screen flex flex-col items-center justify-center
        bg-gray-100 px-6 text-center">
            <ErrorOutlineOutlinedIcon
                sx={{ fontSize: 90 }}
                className="text-gray-500"
            />
            <h1 className="text-8xl font-extrabold text-gray-300">
                500
            </h1>

            <h2 className="mt-4 text-3xl font-bold text-gray-800">
                Error al cargar
            </h2>

            <p className="mt-2 max-w-md text-gray-600">
                La página no pudo cargarse correctamente
            </p>

            <Link
                to="/"
                className="mt-8 rounded-lg bg-blue-600 px-6 py-3 
                font-medium text-white transition hover:bg-blue-700"
            >
                Volver al inicio
            </Link>
        </div>
    );
};
