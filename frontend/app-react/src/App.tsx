import { RouterProvider } from "react-router-dom";
import { AppRouter } from "@/routes/AppRouter";
import { Toaster } from "react-hot-toast";

export const App = () => {
  return (
    <>
      <Toaster
        position="top-right"
        reverseOrder={false}
      />
      <RouterProvider router={AppRouter} />
    </>
  )
}