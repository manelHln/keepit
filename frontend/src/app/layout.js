import "./globals.css";
import { Inter } from "next/font/google";
import React from "react";
import { Toaster } from "@/components/ui/toaster";

const inter = Inter({ subsets: ["latin"] });

export const metadata = {
  title: "keepIt",
  description: "Your handy Notebook",
};

export default function RootLayout({ children }) {

  return (
    <html lang="en">
      <body>
      {children}
      <Toaster />
      </body>
    </html>
  );
}
