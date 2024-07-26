import imgCat from "../../assets/Cat.jpg"
import { useImageSize } from "react-image-size";
import { useEffect } from "react";

export default function Cat(){
    // const [dimensions, {loading, error}] = useImageSize("https://th.bing.com/th/id/OIP.5aKhA8rhSGnJzNnkGJgtLwHaLH?rs=1&pid=ImgDetMain");
    //const [dimensions] = useImageSize("../assets/Dog.jpg");
    const [dimensions, {loading, error}] = useImageSize(imgCat);

    let myWidth;
    let myHeight = 200;

    useEffect(() =>{
        console.log("USE EFFECT\n" + JSON.stringify(dimensions));
        myWidth = myHeight / dimensions?.height;
    }, [dimensions])

    if(loading){
        return <p>Loading...</p>
    }

    if(error){
        return <>
            <p>ERROR</p>
            <p>{error}</p>
        </>
    }

    return (<>
        <h1>Cat</h1>
        <div>
            <img src={imgCat} height={myHeight} width={myWidth} alt="cat"></img>
        </div>
    </>)
}