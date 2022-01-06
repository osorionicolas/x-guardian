import React, { useEffect, useState } from "react";
import {
  Box,
  Heading,
  Flex,
  Button
} from "@chakra-ui/react";
import { useLocation } from "wouter";

const Header = (props) => {
  const [location, setLocation] = useLocation();
  const [action, setAction] = useState({
    url: "/dashboard",
    buttonText: "Dashboard"
  })

  useEffect(() => {
    location === "/" ? setAction({url: "/dashboard", buttonText: "Dashboard"}) : setAction({ url: "/", buttonText: "Alerts" })
  }, [location]);

  return (
    <Flex
      as="nav"
      align="center"
      justify="space-between"
      wrap="wrap"
      padding={5}
      bg="teal.500"
      color="white"
      {...props}
    >
      <Flex align="center">
        <Heading as="h1" size="lg" letterSpacing={"tighter"}>
          X-Guardian
        </Heading>
      </Flex>

      <Box
        display={{ base: "block", md: "block" }}
      >
        <Button
          colorScheme='white'
          variant="outline"
          _hover={{ bg: "teal.700", borderColor: "teal.700" }}
          onClick={() => setLocation(action.url)}
        >
          {action.buttonText}
        </Button>
      </Box>
    </Flex>
  );
};

export default Header;
