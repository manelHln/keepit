import React from "react";
import Icon from "./Icon";
import { Button } from "@/components/ui/button"


const Navbar = () => {
  return (
    <div className="w-full flex justify-between items-center border-b border-slate-800">
      <label className="py-4 px-3 relative text-gray-400 focus-within:text-gray-600 block">
        <Icon
          name="search"
          size={16}
          className="pointer-events-none absolute top-1/2 transform -translate-y-1/2 left-3"
        />
        <input
          type="text"
          placeholder="Search your notes..."
          className="form-input placeholder-gray-400 text-gray-500 appearance-none w-full block pl-6 focus:outline-none"
        />
      </label>
      <div className="flex items-center">
        <div className="flex mr-3 items-center">
          <span className="mr-2">Emmanuel Holonou</span>
          <div className="p-2 rounded-full bg-slate-400">
            <Icon name="user-2" size={16} />
          </div>
        </div>
        <Button>
          Logout <Icon name="log-out" size={16} />
        </Button>
        {/* <div className="flex mr-3 items-center">
          <span className="mr-2">Logout</span>
          <div className="p-2 rounded-full bg-slate-400">
            <Icon name="log-out" size={16} />
          </div>
        </div> */}
      </div>
    </div>
  );
};

export default Navbar;
