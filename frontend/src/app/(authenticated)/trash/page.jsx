"use client";

import React from "react";
import Image from "next/image";
import Header from "@/components/Header";
import NoteCard from "@/components/NoteCard";
import axiosRequest from "@/utils/axiosRequest";
import { useUserInfoStore } from "@/store/userInfoStore";
import store from "@/store/globalStore";

const page = () => {
  const fetchUserInfo = useUserInfoStore((state) => state.fetchUserInfo);
  const userInfo = useUserInfoStore((state) => state.userInfo);
  const searchQuery = store((state)=> state.searchQuery)
  const shouldUpdate = store((state)=> state.shouldRefetchData);
  const setShouldUpdate = store((state)=> state.setShouldRefetchData);
  const [notes, setNotes] = React.useState(null);

  React.useEffect(() => {
    setShouldUpdate(true)
    fetchUserInfo();
  }, []);

    React.useEffect(()=>{
    if(!searchQuery) return;
    axiosRequest
      .get(`note/search?q=${searchQuery}&s=TRASHED&u=${userInfo?.id}`)
      .then((response)=> {
        setNotes(response.data)
      })
  }, [searchQuery])

  React.useEffect(() => {
    if (!userInfo || !shouldUpdate) return;
    axiosRequest
      .get(`/note/trashes/${userInfo?.id}`)
      .then((response) => {
        setNotes(response.data);
      })
      .catch((error) => console.log(error))
      .finally(() => setShouldUpdate(false));
  }, [userInfo, shouldUpdate]);

  return (
    <div className="h-full relative">
      <Header
        title={`Welcome back, ${userInfo?.firstname}`}
        icon="trash"
        subtile="My trash"
      />
      {notes ? (
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
          {notes.map((note) => {
            console.log(note);
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
        <div className="absolute top-1/2 left-1/2 translate-x-[-50%] translate-y-[-50%] flex items-center justify-center w-full">
          <Image width={80} height={80} src="/note.png" alt="Notebook Icon" />
          <p className="text-2xl text-slate-700 font-normal">
            Nothing to see here
          </p>
        </div>
      )}
    </div>
  );
};

export default page;
