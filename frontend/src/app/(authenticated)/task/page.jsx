"use client";

import React from "react";
import Header from "@/components/Header";
import axiosRequest from "@/utils/axiosRequest";
import { useUserInfoStore } from "@/store/userInfoStore";

const page = () => {
  const fetchUserInfo = useUserInfoStore((state) => state.fetchUserInfo);
  const userInfo = useUserInfoStore((state) => state.userInfo);

  React.useEffect(() => {
    fetchUserInfo();
  }, []);

  return (
    <div className="h-full relative">
      <Header
        title={`Welcome back, ${userInfo?.firstname}`}
        icon="archive"
        subtile="My tasks"
      />
    </div>
  );
};

export default page;
