import { create } from "zustand";

const store = create((set)=>({
    shouldRefetchData: false,
    searchQuery: "",
    setSearchQuery: val => set(() => ({searchQuery: val})),
    setShouldRefetchData: val => set(() => ({ shouldRefetchData: val })),
}))

export default store;