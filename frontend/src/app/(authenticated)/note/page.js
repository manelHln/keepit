"use client";

import React from "react";
import Image from "next/image";
import NoteCard from "@/components/NoteCard";
import Header from "@/components/Header";
import axiosRequest from "@/utils/axiosRequest";
import { useUserInfoStore } from "@/store/userInfoStore";
import store from "@/store/globalStore";

export default function Home() {
  const userInfo = useUserInfoStore(state => state.userInfo)
  const fetchUserInfo = useUserInfoStore((state)=> state.fetchUserInfo)
  const searchQuery = store((state)=> state.searchQuery)
  const shouldUpdate = store((state)=> state.shouldRefetchData);
  const setShouldUpdate = store((state)=> state.setShouldRefetchData);
  const [notes, setNotes] = React.useState(null);

  React.useEffect(()=>{
    setShouldUpdate(true)
    fetchUserInfo()
  }, [])

  React.useEffect(()=>{
    if(!searchQuery) return;
    axiosRequest
      .get(`note/search?q=${searchQuery}&s=DEFAULT&u=${userInfo?.id}`)
      .then((response)=> {
        setNotes(response.data)
      })
  }, [searchQuery])

  React.useEffect(() => {
    if (!userInfo || !shouldUpdate) return;
    console.log(userInfo)
    axiosRequest
      .get(`/note/${userInfo?.id}`)
      .then((response) => {
        console.log(response.data)
        setNotes(response.data);
      })
      .catch((error) => console.log(error))
      .finally(() => setShouldUpdate(false));
  }, [userInfo, shouldUpdate]);

  return (
    <div className="h-full relative">
      <Header
        title={`Welcome back, ${userInfo?.firstname}`}
        subtile="My notes"
        icon="file"
      />

      {notes?.length > 0 ? (
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
          {notes?.map((note) => {
            return (
              <NoteCard
                key={note.id}
                note={note}
                setShouldUpdate={setShouldUpdate}
              />
            );
          })}
        </div>
      ) : (
        <div className="absolute top-1/2 left-1/2 translate-x-[-50%] translate-y-[-50%] flex items-center justify-center w-full gap-2">
          <Image width={80} height={80} src="/note.png" alt="Notebook Icon" />
          <p className="text-2xl text-slate-700 font-normal">
            Nothing to see here
          </p>
        </div>
      )}
    </div>
  );
}
