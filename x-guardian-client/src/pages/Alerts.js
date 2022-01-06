import React, { useEffect } from 'react'
import { Icon, Box, Grid } from '@chakra-ui/react'
import { MdOutlinePets } from 'react-icons/md'
import { GiRobber } from 'react-icons/gi'
import { RiFloodFill } from 'react-icons/ri'
import { BsLightbulbOffFill } from 'react-icons/bs'
import Card from '../components/Card'

export const Alerts = () => {

    const fetchData = async () => {
        /*try {
            
            axios(`${API_URL}/recommendations`).then(response => {
                    const data = response.data
                    const keys = Object.keys(data)
                    const randomObject = Math.floor(Math.random()*keys.length)
                    const courses = data[keys[randomObject]]
                    setRecommendations(courses)
                })

                axios(`${API_URL}/topics`).then(topicsResponse => setTopics(topicsResponse.data))
                axios(`${API_URL}/users/interests`).then(interestsResponse => setInsterests(interestsResponse.data))
            }
            else {
                axios(`${API_URL}/visitors/recommendations`).then(response => setRecommendations(response.data.discover))
            }
            
        }
        catch(error){
            console.log(error)          
        }*/
    }
    
    useEffect(() => {
        fetchData()
    }, [])

    return (
        <Box display='flex' height='calc(100vh - 80px)' justifyContent='center' alignItems='center' flexWrap='wrap'>
            <Grid gridTemplateColumns='repeat(auto-fit, minmax(20rem, 1fr))' w='100%' gap='4rem' p='4rem'>
                <Card title="Crime" icon={<Icon as={GiRobber} boxSize={28}/>}></Card>
                <Card title="Stray" icon={<Icon as={MdOutlinePets} boxSize={28}/>}></Card>
                <Card title="Flood" icon={<Icon as={RiFloodFill} boxSize={28}/>}></Card>
                <Card title="Outage" icon={<Icon as={BsLightbulbOffFill} boxSize={28}/>}></Card>
            </Grid>
        </Box>
    )
}