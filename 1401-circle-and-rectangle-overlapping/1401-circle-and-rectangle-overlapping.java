class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find the closest X coordinate on the rectangle to the circle's center
        int closestX = Math.max(x1, Math.min(xCenter, x2));
        
        // Find the closest Y coordinate on the rectangle to the circle's center
        int closestY = Math.max(y1, Math.min(yCenter, y2));
        
        // Calculate the distance vector between the circle's center and this closest point
        int distX = xCenter - closestX;
        int distY = yCenter - closestY;
        
        // Use the Pythagorean theorem (squared) to avoid floating-point inaccuracies
        int distanceSquared = (distX * distX) + (distY * distY);
        
        // If the squared distance is less than or equal to radius squared, they overlap
        return distanceSquared <= (radius * radius);
    }
}
