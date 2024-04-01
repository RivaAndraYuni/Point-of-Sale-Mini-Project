import React from "react";
import OrderProduct from "./pages/OrderProduct";
import { Route, Routes } from "react-router-dom";
import Pembayaran from "./pages/Pembayaran";
import DaftarProduct from "./pages/DaftarProduct";
import FormProduct from "./pages/FormProduct";
import DetailProduct from "./pages/DetailProduct";

export default function App() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<OrderProduct />} />
        <Route path="/pembayaran" element={<Pembayaran />} />
        <Route path="/daftar-produk" element={<DaftarProduct />} />
        <Route path="/form-produk" element={<FormProduct />} />
        <Route path="/form-produk/edit/:id" element={<FormProduct />} />
        <Route path="/detail-produk/:id" element={<DetailProduct />} />
      </Routes>
    </div>
  );
}
