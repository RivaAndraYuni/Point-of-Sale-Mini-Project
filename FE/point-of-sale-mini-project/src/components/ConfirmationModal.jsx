import { X } from "lucide-react";
import React from "react";

export default function ConfirmationModal({
  message,
  onConfirm,
  onCancel,
  id,
}) {
  const handleConfirm = () => {
    onConfirm(id);
  };

  const handleClose = () => {
    onCancel();
  };

  return (
    <>
      <div className="fixed inset-0 flex items-center justify-center">
        <div className="absolute inset-0 bg-gray-900 opacity-50"></div>
        <div className="w-[500px] bg-white border rounded-lg z-50">
          <div className="flex justify-end p-2">
            <button onClick={handleClose}>
              <X className="hover:scale-90 cursor-pointer" />
            </button>
          </div>

          <div className="text-center m-2 p-1">{message}</div>
          <div className="flex justify-evenly p-2">
            <button
              className="py-2 w-24 bg-yellow-300 border border-yellow-800 rounded-lg font-bold hover:bg-yellow-500"
              onClick={handleConfirm}
            >
              Ya
            </button>
            <button
              className="py-2 w-24 bg-yellow-300 border border-yellow-800 rounded-lg font-bold hover:bg-yellow-500"
              onClick={handleClose}
            >
              Tidak
            </button>
          </div>
        </div>
      </div>
    </>
  );
}
