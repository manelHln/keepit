import Sidebar from "@/components/Sidebar";

export default function RootLayout({ children }) {

  return (
    <div className="w-full flex">
      <Sidebar />
      <div className="bg-slate-100 p-4 w-full">{children}</div>
    </div>
  );
}
