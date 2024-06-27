const Skeleton = () => {
    return (
        <div className="grid grid-cols-3 w-full gap-8 mt-6">
            <div className="space-y-2">
                <div className="skeleton bg-neutral-900 w-full h-56"></div>
                <div className="skeleton bg-neutral-900 w-full h-32"></div>
            </div>
            <div className="space-y-2">
                <div className="skeleton bg-neutral-900 w-full h-56"></div>
                <div className="skeleton bg-neutral-900 w-full h-32"></div>
            </div>
            <div className="space-y-2">
                <div className="skeleton bg-neutral-900 w-full h-56"></div>
                <div className="skeleton bg-neutral-900 w-full h-32"></div>
            </div>
        </div>
    );
};

export default Skeleton;
