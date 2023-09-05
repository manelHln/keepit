import React from "react";
import Icon from "./Icon";

const Header = ({ title, subtile, icon }) => {
  return (
    <div className="flex flex-col gap-4 mb-2">
      <h1 className="text-slate-600 font-medium text-xl capitalize">{title}</h1>
      <div className="flex gap-1">
        <Icon name={icon} size={16} className="text-slate-500" />
        <h3 className="text-slate-500 font-normal text-sm">{subtile}</h3>
      </div>
    </div>
  );
};

export default Header;
