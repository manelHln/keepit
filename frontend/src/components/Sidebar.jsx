"use client";

import Link from "next/link";
import { useRouter } from "next/navigation";
import React from "react";
import Icon from "./Icon";
import InputWithIcon from "./InputWithIcon";
import { Button } from "@/components/ui/button";
import { Avatar, AvatarFallback, AvatarImage } from "./ui/avatar";
import { Modal } from "./Modal";
import AddNoteForm from "./AddNoteForm";
import { useUserInfoStore } from "@/store/userInfoStore";
import store from "@/store/globalStore";

const Sidebar = () => {
  const [isClosed, setIsClosed] = React.useState(false);
  const fetchUserInfo = useUserInfoStore((state) => state.fetchUserInfo);
  const userInfo = useUserInfoStore((state) => state.userInfo);
  const setSearchQuery = store((state)=> state.setSearchQuery)
  const [isModalOpen, setIsModalOPen] = React.useState(false);
  const router = useRouter();

  React.useEffect(() => {
    fetchUserInfo();
  }, []);

  const handleLogout = (e) => {
    e.preventDefault();
    localStorage.removeItem("keepit_access_token");
    router.push("/");
  };

  const closeModal = () => setIsModalOPen(false);

  return (
    <div
      className={`flex flex-col justify-between h-screen sticky top-0 pb-4 border-r border-slate-300 duration-300 ${
        isClosed ? "w-16" : "w-1/6"
      }`}
    >
      <div>
        <div className="flex border-b border-slate-300 p-4 relative">
          <div className="p-1 rounded-full bg-red-100 cursor-pointer absolute bottom-[-10px] right-[-8px]">
            <Icon
              name={isClosed ? "chevrons-right" : "chevrons-left"}
              size={16}
              className="text-red-400 font-normal"
              onClick={() => {
                setIsClosed(!isClosed);
              }}
            />
          </div>
          <Avatar>
            <AvatarImage src="man.png"></AvatarImage>
            <AvatarFallback>CN</AvatarFallback>
          </Avatar>
          {!isClosed && (
            <div className="flex flex-col justify-between ml-4 max-w-full">
              <span className="text-slate-500 font-normal text-sm">
                {userInfo?.firstname} {userInfo?.lastname}
              </span>
              <span className="text-slate-500 font-normal text-sm text-ellipsis overflow-hidden truncate w-8/12">
                {userInfo?.email}
              </span>
            </div>
          )}
        </div>

        <Modal
          title={"Add new note"}
          isOpen={isModalOpen}
          onclose={() => setIsModalOPen(prev => !prev)}
          triggerButton={
            <div className={`${isClosed ? "px-2" : "px-4"}`}>
              <Button
                size="icon"
                className="flex justify-center items-center gap-2 rounded-sm bg-red-600 hover:bg-red-500 w-full mt-4"
                onClick={() => setIsModalOPen(true)}
              >
                <Icon name="plus" size={16} /> {!isClosed && "Add note"}
              </Button>
            </div>
          }
          description="Add new note"
          body={<AddNoteForm closeModal={closeModal} />}
          footer={""}
        />

        <div className="px-4">
          {!isClosed ? (
            <InputWithIcon
              name={"search"}
              placeholder={"Search your notes..."}
              size={16}
              classnames={"my-4 border-slate-300 border rounded-md"}
              onChange={(e)=> setSearchQuery(e.target.value)}
            />
          ) : (
            <div className="p-2" onClick={() => setIsClosed(false)}>
              <Icon name="search" size={20} />
            </div>
          )}
        </div>

        <div className="flex flex-col gap-1 px-4">
          <Link
            href="/note"
            className="flex items-center font-normal text-sm text-slate-700 hover:bg-slate-200 rounded-md p-2"
          >
            <Icon name="clipboard-edit" size={18} />
            {!isClosed && <span className="pl-1">Notes</span>}
          </Link>
          {/* <Link */}
          {/*   href="/task" */}
          {/*   className="flex items-center font-normal text-sm text-slate-700 hover:bg-slate-200 rounded-md p-2" */}
          {/* > */}
          {/*   <Icon name="clipboard-edit" size={18} /> */}
          {/*   {!isClosed && <span className="pl-1">Tasks</span>} */}
          {/* </Link> */}
          {/* <Link */}
          {/*   href="/note/code" */}
          {/*   className="flex items-center font-normal text-sm text-slate-700 hover:bg-slate-200 rounded-md p-2" */}
          {/* > */}
          {/*   <Icon name="bookmark" size={18} /> */}
          {/*   {!isClosed && <span className="pl-1">Tag</span>} */}
          {/* </Link> */}
          {/* <Link */}
          {/*   href="/note/other" */}
          {/*   className="flex items-center font-normal text-sm text-slate-700 hover:bg-slate-200 rounded-md p-2" */}
          {/* > */}
          {/*   <Icon name="bookmark" size={18} /> */}
          {/*   {!isClosed && <span className="pl-1">Tag</span>} */}
          {/* </Link> */}
          <Link
            href="/archive"
            className="flex items-center font-normal text-sm text-slate-700 hover:bg-slate-200 rounded-md p-2"
          >
            <Icon name="archive" size={18} />
            {!isClosed && <span className="pl-1">Archives</span>}
          </Link>
          <Link
            href="trash"
            className="flex items-center text-md font-normal text-sm text-slate-700 hover:bg-slate-200 rounded-md p-2"
          >
            <Icon name="trash-2" size={18} />
            {!isClosed && <span className="pl-1">Trash</span>}
          </Link>
        </div>
      </div>

      <div className="px-4">
        <Link
          href="/logout"
          className="flex items-center text-sm font-normal text-slate-700 hover:bg-slate-200 rounded-md p-2"
          onClick={handleLogout}
        >
          <Icon name="log-out" size={18} />
          {!isClosed && <span className="pl-1">Logout</span>}
        </Link>
      </div>
    </div>
  );
};

export default Sidebar;
