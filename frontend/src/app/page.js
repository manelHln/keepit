"use client";

import { useEffect } from "react";
import { useRouter } from "next/navigation";
import LoadingEffect from "@/components/LoadingEffect";
import { useUserInfoStore } from "@/store/userInfoStore";


export default function Home() {
  const router = useRouter();
  const fetchUserInfo = useUserInfoStore((state)=> state.fetchUserInfo)

  useEffect(() => {
    const token = localStorage.getItem("keepit_access_token");
    if (!token) {
      router.push("/login");
    } else {
      fetchUserInfo()
      router.push("/note");
    }
  }, []);

  return <LoadingEffect />;
}
