// eslint-disable-next-line @typescript-eslint/no-explicit-any
const Skeleton = () => {
    return (
        <div className="flex flex-col pt-8 gap-4 h-[calc(100vh-100px)]">
            <div className="flex justify-between">
                <div className="skeleton bg-neutral-900 w-[50%] h-16"></div>
                <div className="flex gap-6">
                    <div className="skeleton bg-neutral-900 w-40 h-16"></div>
                    <div className="skeleton bg-neutral-900 w-40 h-16"></div>
                </div>
            </div>
            <div className="grid gap-7 grid-cols-[auto_max(30vw,300px)] h-full">
                <div className="skeleton bg-neutral-900 h-full w-full"></div>
                <div className="flex flex-col gap-4 w-full">
                    <div className="flex flex-col gap-4 w-full">
                        <div className="skeleton bg-neutral-900 h-8 w-3/4"></div>
                        <div className="skeleton bg-neutral-900 h-4 w-full"></div>
                        <div className="skeleton bg-neutral-900 h-4 w-full"></div>
                        <div className="skeleton bg-neutral-900 h-4 w-full"></div>
                        <div className="skeleton bg-neutral-900 h-4 w-full"></div>
                        <div className="skeleton bg-neutral-900 h-4 w-full"></div>
                        <div className="skeleton bg-neutral-900 h-4 w-full"></div>
                        <div className="skeleton bg-neutral-900 h-4 w-full"></div>
                        <div className="skeleton bg-neutral-900 h-4 w-full"></div>
                    </div>
                    <div className="skeleton bg-neutral-900 w-full h-full"></div>
                </div>
            </div>
        </div>
    );
};

export default Skeleton;
