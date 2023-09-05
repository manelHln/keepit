import React from "react";

const CustomBadge = ({ content, bgColor, textColor }) => {
  return (
    <div className={`rounded-sm py-1 px-2 ${bgColor}`}>
      <span className={`text-xs font-normal ${textColor}`}>{content}</span>
    </div>
  );
};

export default CustomBadge;
