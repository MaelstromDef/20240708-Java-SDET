import { useEffect } from "react";
import imgDog from "../../assets/Dog.jpg"
import {useImageSize} from 'react-image-size'

export default function Dog(){
    const [dimensions, {loading, error}] = useImageSize(imgDog);

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
        <h1>Dog</h1>
        <div>
            <img src={imgDog} height={myHeight} width={myWidth} alt="dog"></img>
        </div>
    </>)
}