"use client";

import React from "react";
import axiosRequest from "@/utils/axiosRequest";
import { Button } from "./ui/button";
import { Editor } from "@tinymce/tinymce-react";
import { useToast } from "./ui/use-toast";
import { useUserInfoStore } from "@/store/userInfoStore";
import store from "@/store/globalStore";

const AddNoteForm = ({ closeModal }) => {
  const { toast } = useToast();
  const userInfo = useUserInfoStore((state) => state.userInfo);
  const setShouldUpdate = store((state)=> state.setShouldRefetchData);
  const editorRef = React.useRef();
  const handleSubmit = (e) => {
    e.preventDefault();
    if (editorRef.current) {
      if (!editorRef.current.getContent()) {
        toast({
          title: "OOps",
          description: "Please field cannot be empty!",
          variant: "destructive",
        });
        return;
      }
      const data = {
        content: editorRef.current.getContent(),
        userId: userInfo.id,
      };
      axiosRequest
        .post("/note", data)
        .then((response) => {
          toast({
            title: "Great!",
            description: "Note added successfully!",
          });
          setShouldUpdate(true)
        })
        .catch((error) => {
          toast({
            title: "Oops, something went wrong",
            description: error.message,
            variant: "destructive",
          });
        })
        .finally(() => closeModal());
    }
  };

  return (
    <form className="grid gap-4" onSubmit={handleSubmit}>
      <Editor
        apiKey={process.env.NEXT_PUBLIC_TINYMCE_KEY}
        onInit={(event, editor) => (editorRef.current = editor)}
        init={{
          height: 300,
          menubar: false,
          plugins: [
            "advlist autolink lists link image charmap print preview anchor",
            "searchreplace visualblocks code fullscreen",
            "insertdatetime media table code help wordcount",
          ],
          toolbar:
            "undo redo | formatselect | " +
            "bold italic backcolor | alignleft aligncenter " +
            "alignright alignjustify | bullist numlist outdent indent | " +
            "removeformat | help",
          content_style:
            "body { font-family:Helvetica,Arial,sans-serif; font-size:14px }",
        }}
      />
      <div>
        <Button
          type="submit"
          size="icon"
          className="flex justify-center items-center gap-2 rounded-sm px-10"
        >
          Save
        </Button>
      </div>
    </form>
  );
};

export default AddNoteForm;
