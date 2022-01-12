import { useEffect, useState, useRef } from 'react'
import ChartsEmbedSDK from '@mongodb-js/charts-embed-dom';
import { Box } from '@chakra-ui/react';

const Chart = ({chartId, height, width}) => {

    const sdk = new ChartsEmbedSDK({baseUrl: 'https://charts.mongodb.com/charts-project-0-swhvr'});
    const chartDiv = useRef(null);
    // eslint-disable-next-line no-unused-vars
    const [rendered, setRendered] = useState(false);
    const [chart] = useState(sdk.createChart({chartId: chartId, height: height, width: width, theme: "dark"}));
    
    useEffect(() => {
        chart.render(chartDiv.current).then(() => setRendered(true)).catch(err => console.log("Error during Charts rendering.", err));
    }, [chart]);

    return <Box className="chart" ref={chartDiv}/>;
}

export default Chart;