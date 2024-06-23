// eslint-disable-next-line @typescript-eslint/no-explicit-any
const Skeleton = () => {
    return (
        <div className="px-24 py-4 flex flex-col gap-4 h-[calc(100vh-90px)]">
            <div className="flex justify-between">
                <div className="skeleton w-[50%] h-16"></div>
                <div className="flex gap-6">
                    <div className="skeleton w-40 h-16"></div>
                    <div className="skeleton w-40 h-16"></div>
                </div>
            </div>
            <div className="grid gap-7 grid-cols-[auto_max(30vw,300px)] h-full">
                <div className="skeleton h-full w-full"></div>
                <div className="flex flex-col gap-4 w-full">
                    <div className="flex flex-col gap-4 w-full">
                        <div className="skeleton h-8 w-3/4"></div>
                        <div className="skeleton h-4 w-full"></div>
                        <div className="skeleton h-4 w-full"></div>
                        <div className="skeleton h-4 w-full"></div>
                        <div className="skeleton h-4 w-full"></div>
                        <div className="skeleton h-4 w-full"></div>
                    </div>
                    <div className="skeleton w-full h-full"></div>
                </div>
            </div>
        </div>
    );
};

export default Skeleton;
