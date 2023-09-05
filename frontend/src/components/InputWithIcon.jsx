import React from "react";
import Icon from "./Icon";

const InputWithIcon = ({ placeholder, name, size, classnames, onChange }) => {
  return (
      <div className={`relative h-10 w-full ${classnames}`}>
        <div className="absolute top-2/4 left-1 grid h-5 w-5 -translate-y-2/4 place-items-center text-blue-gray-500">
          <Icon name={name} size={size} />
        </div>
        <input
          className="h-full w-full bg-transparent pl-8 py-2.5 font-sans text-sm font-normal outline-none"
          placeholder={placeholder}
          onChange={onChange}
        />
      </div>
  );
};

export default InputWithIcon;
