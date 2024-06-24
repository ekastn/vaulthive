import { useQuery } from "@tanstack/react-query";
import { getListsApi } from "../../services/ListService";

const useLists = () => {
    const { data: lists, isLoading, error } = useQuery({
        queryKey: ["lists"],
        queryFn: async () => getListsApi(),
    });

    return { lists, isLoading, error };
};

export default useLists;