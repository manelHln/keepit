import React, { useState } from "react";
import { Input } from "./ui/input";
import { Button } from "./ui/button";
import axiosRequest from "@/utils/axiosRequest";
import { useToast } from "./ui/use-toast";
import store from "@/store/globalStore";

const AddTagForm = ({ noteId }) => {
  const { toast } = useToast();
  const setShouldUpdate = store((state)=> state.setShouldRefetchData);
  const [tagname, setTagname] = useState("");

  const handleAddTag = (e) => {
    const data = {
      tagname: tagname,
      noteId: noteId,
    };
    axiosRequest
      .post("/tag", data)
      .then((response) => {
        toast({
          title: "Great!",
          description: "Tag added successfully!",
        });
        setShouldUpdate(true)
      })
      .catch((error) => {
        toast({
          title: "Oops, something went wrong",
          description: error.message,
          variant: "destructive",
        });
      });
  };

  return (
    <div className="grid gap-4">
      <div className="space-y-2">
        <h4 className="font-medium leading-none">Tag</h4>
        <p className="text-sm text-muted-foreground">Add a tag to your note</p>
      </div>
      <div className="grid gap-2">
        <div className="grid items-center gap-4">
          <Input
            id="width"
            value={tagname}
            className="col-span-2 h-8"
            name="tagname"
            onChange={(e) => setTagname(e.target.value)}
          />
        </div>
        <Button
          size="icon"
          className="flex justify-center items-center gap-2 rounded-sm px-10"
          onClick={handleAddTag}
        >
          Done
        </Button>
      </div>
    </div>
  );
};

export default AddTagForm;
