import Spinner from "./Spinner";

const FullScreenLoading = () => {
    return (
        <div className="fixed top-0 left-0 z-50 flex items-center justify-center w-full h-full bg-black bg-opacity-75">
            <Spinner />
        </div>
    );
};

export default FullScreenLoading;
