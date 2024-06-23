const Skeleton = () => {
    return (
        <div className="grid grid-cols-3 w-full gap-8 mt-6">
            <div className="space-y-2">
                <div className="skeleton w-full aspect-video"></div>
                <div className="skeleton w-1/2 h-4"></div>
                <div className="skeleton w-full h-10"></div>
            </div>
            <div className="space-y-2">
                <div className="skeleton w-full aspect-video"></div>
                <div className="skeleton w-1/2 h-4"></div>
                <div className="skeleton w-full h-10"></div>
            </div>
            <div className="space-y-2">
                <div className="skeleton w-full aspect-video"></div>
                <div className="skeleton w-1/2 h-4"></div>
                <div className="skeleton w-full h-10"></div>
            </div>
        </div>
    );
};

export default Skeleton;
