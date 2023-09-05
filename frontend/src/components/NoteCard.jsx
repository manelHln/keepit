import React from "react";
import {
  Card,
  CardContent,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import CustomBadge from "./CustomBadge";
import TooltipButton from "./TooltipButton";
import { CustomPopover } from "./CustomPopover";
import Icon from "./Icon";
import AddTagForm from "./AddTagForm";
import axiosRequest from "@/utils/axiosRequest";
import { useToast } from "./ui/use-toast";

function formatDate(inputDateString) {
  const date = new Date(inputDateString);

  const months = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
  ];

  const day = date.getDate();
  const monthIndex = date.getMonth();
  const year = date.getFullYear();

  const formattedDate = `${day} ${months[monthIndex]} ${year}`;
  return formattedDate;
}

const colors = [
  {
    bg: "bg-blue-100",
    text: "text-blue-400",
  },
  {
    bg: "bg-red-100",
    text: "text-red-400",
  },
  {
    bg: "bg-green-100",
    text: "text-green-400",
  },
  {
    bg: "bg-orange-100",
    text: "text-orange-400",
  },
  {
    bg: "bg-purple-100",
    text: "text-purple-400",
  },
  {
    bg: "bg-yellow-100",
    text: "text-yellow-400",
  },
];

const NoteCard = ({ note, setShouldUpdate }) => {
  const date = formatDate(note.createdAt);
  const { toast } = useToast();

  const moveToTrash = () => {
    axiosRequest
      .put(`/note/${note.id}/trash`)
      .then((response) => {
        setShouldUpdate(true);
        toast({
          title: "Success",
          description: "Note successfully moved to trash!",
        });
      })
      .catch((err) => {
        alert(err);
        toast({
          title: "Oops, something went wrong",
          description: err,
          variant: "destructive",
        });
      });
  };

  const moveToArchive = () => {
    axiosRequest
      .put(`/note/${note.id}/archive`)
      .then((response) => {
        setShouldUpdate(true);
        toast({
          title: "Success",
          description: "Note successfully moved to archive!",
        });
      })
      .catch((err) => {
        toast({
          title: "Oops, something went wrong",
          description: err,
          variant: "destructive",
        });
      });
  };

  const pin = () => {
    axiosRequest
      .put(`/note/${note.id}/pin`)
      .then((response) => {
        setShouldUpdate(true);
        toast({
          title: "Success",
          description: "Note successfully added to your favourites!",
        });
      })
      .catch((err) => {
        alert(err);
        toast({
          title: "Oops, something went wrong",
          description: err,
          variant: "destructive",
        });
      });
  };

  return (
    <Card>
      <CardHeader>
        <CardTitle className="flex justify-between items-center">
          <span className="text-slate-500 font-normal text-xs">{date}</span>
          <div className="flex items-center gap-3">
            <CustomPopover
              triggerButton={
                <div className="hover:scale-125 duration-500">
                  <TooltipButton
                    children={
                      <span className="text-slate-500 font-normal text-xs cursor-pointer">
                        <Icon name="tag" size={16} />
                      </span>
                    }
                    content="Add new tag"
                  />
                </div>
              }
              body={<AddTagForm noteId={note.id} />}
            />

            <div
              className="hover:scale-125 duration-500"
              onClick={moveToArchive}
            >
              <TooltipButton
                children={
                  <span className="text-slate-500 font-normal text-xs cursor-pointer">
                    <Icon name="archive" size={16} />
                  </span>
                }
                content="Move to archive"
              />
            </div>
            <div className="hover:scale-125 duration-500" onClick={moveToTrash}>
              <TooltipButton
                children={
                  <span className="text-slate-500 font-normal text-xs cursor-pointer">
                    <Icon name="trash-2" size={16} />
                  </span>
                }
                content="Move to trash"
              />
            </div>
            <div className="hover:scale-125 duration-500" onClick={pin}>
              <TooltipButton
                children={
                  <span className="text-slate-500 font-normal text-xs cursor-pointer">
                    <Icon name="pin" size={16} />
                  </span>
                }
                content="Mark favorite / Pin"
              />
            </div>
          </div>
        </CardTitle>
      </CardHeader>
      <CardContent>
        <div dangerouslySetInnerHTML={{ __html: note?.content }}></div>
      </CardContent>
      <CardFooter className="flex gap-1 flex-wrap">
        {note?.assignedTags?.map((tag) => {
          const randint = Math.floor(Math.random() * colors.length)
          return (
            <CustomBadge
              key={tag.id}
              content={tag.tagName}
              textColor={colors[randint].text}
              bgColor={colors[randint].bg}
            />
          );
        })}
      </CardFooter>
    </Card>
  );
};

export default NoteCard;
