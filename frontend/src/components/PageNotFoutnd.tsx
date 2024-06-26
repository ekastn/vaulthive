const PageNotFoutnd = () => {
    return (
        <div className="absolute inset-0">
            <img
                src="https://external-preview.redd.it/4MddL-315mp40uH18BgGL2-5b6NIPHcDMBSWuN11ynM.jpg?width=960&crop=smart&auto=webp&s=b98d54a43b3dac555df398588a2c791e0f3076d9"
                className="absolute object-cover w-full h-full"
            />
            <div className="absolute inset-0 bg-black opacity-25"></div>
            <div className="relative z-10 flex items-center px-6 mx-auto py-40 h-full">
                <div className="relative z-10 flex flex-col items-center w-full font-mono">
                    <h1 className="mt-4 text-5xl font-extrabold leading-tight text-center text-white">
                        You are all alone here
                    </h1>
                    <p className="font-extrabold text-white text-8xl my-44 animate-bounce">404</p>
                </div>
            </div>
        </div>
    );
};

export default PageNotFoutnd;
