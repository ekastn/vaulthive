import { useState } from "react";
import { IoIosArrowBack, IoIosArrowForward } from "react-icons/io";

type Props = {
    images: string[];
};

const Carousel = ({ images }: Props) => {
    const [imageIndex, setImageIndex] = useState(0);

    const nextImage = () => setImageIndex((prev) => (prev === images.length - 1 ? 0 : prev + 1));
    const prevImage = () => setImageIndex((prev) => (prev === 0 ? images.length - 1 : prev - 1));

    return (
        <div className="relative w-full h-full overflow-hidden">
            <div
                className="w-full h-full flex transition-transform ease-out duration-500"
                style={{ transform: `translateX(-${imageIndex * 100}%)` }}
            >
                {images.map((image, index) => (
                    <img key={index} src={image} className="skeleton rounded-md object-cover" />
                ))}
            </div>
            <div className="absolute flex justify-between transform -translate-y-1/2 left-5 right-5 top-1/2">
                <button
                    onClick={prevImage}
                    className="p-1 rounded-full shadow bg-gray-400/40 opacity-65 text-white hover:opacity-80"
                >
                    <IoIosArrowBack className="text-4xl cursor-pointer" />
                </button>
                <button
                    onClick={nextImage}
                    className="p-1 rounded-full shadow bg-gray-400/40 opacity-65 text-white hover:opacity-80"
                >
                    <IoIosArrowForward className="text-4xl cursor-pointer" />
                </button>
            </div>
            <div className="absolute bottom-4 right-0 left-0">
                <div className="flex items-center justify-center gap-2">
                    {images.map((_, i) => (
                        <div
                            className={`transition-all w-3 h-3 bg-base-content rounded-full ${imageIndex === i ? "p-2 w-10" : "bg-opacity-50"} `}
                        />
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Carousel;
