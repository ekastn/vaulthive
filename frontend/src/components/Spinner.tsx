const Spinner = ({ className }: { className?: string }) => {
    return (
        <div role="status" className={`text-center p-2 ${className ? className : ""}`}>
            <span className="loading loading-bars loading-lg"></span>
        </div>
    );
};

export default Spinner;
